#!/usr/bin/groovy
import groovy.xml.MarkupBuilder

def lines = System.in.newReader().readLines()
// Only get INFO lines and the module header
        .grep { String line -> line.startsWith('[INFO]   ') || line.contains('Building ')}
// Remove the INFO tag
        .collect { String line -> (line.split(' ') - '[INFO]').join(' ') }

lines = lines
// Fix lines that has wrapped
        .eachWithIndex { String line, int idx ->
            if(!line.contains('...') && line.contains('->')){
                lines[idx-1] << line
                lines[idx] = ''
            }
        }

// Only get version upgrades and module header
        .grep { it.contains('->') || it.startsWith('Building')}
// Replace dots and split into list of arrays
        .collect { it
                .replaceAll('(\\.){2,}', ':')
                .replaceAll('->', ':')
                .replace('Building ','')
                .split(':')
        }
// Render HTML
new MarkupBuilder().html {
    head {
        style {
            mkp.yield """
                   .datagrid table { border-collapse: collapse; text-align: left; width: 100%; } 
                   .datagrid {font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; border: 1px solid #36752D; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; }
                   .datagrid table td, 
                   .datagrid table th { padding: 3px 10px; }
                   .datagrid table thead th {background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #36752D), color-stop(1, #275420) );background:-moz-linear-gradient( center top, #36752D 5%, #275420 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#36752D', endColorstr='#275420');background-color:#36752D; color:#FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #36752D; } 
                   .datagrid table thead th:first-child { border: none; }
                   .datagrid table tbody td { color: #275420; border-left: 1px solid #C6FFC2;font-size: 12px;font-weight: normal; }
                   .datagrid table tbody .alt td { background: #DFFFDE; color: #275420; }
                   .datagrid table tbody td:first-child { border-left: none; }
                   .datagrid table tbody tr:last-child td { border-bottom: none; }
                   .datagrid table tfoot td div { border-top: 1px solid #36752D;background: #DFFFDE;} 
                   .datagrid table tfoot td { padding: 0; font-size: 12px } 
                   .datagrid table tfoot td div{ padding: 2px; }
                   .datagrid table tfoot td ul { margin: 0; padding:0; list-style: none; text-align: right; }
                   .datagrid table tfoot  li { display: inline; }
                   .datagrid table tfoot li a { text-decoration: none; display: inline-block;  padding: 2px 8px; margin: 1px;color: #FFFFFF;border: 1px solid #36752D;-webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #36752D), color-stop(1, #275420) );background:-moz-linear-gradient( center top, #36752D 5%, #275420 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#36752D', endColorstr='#275420');background-color:#36752D; }
                   .datagrid table tfoot ul.active, .datagrid table tfoot ul a:hover { text-decoration: none;border-color: #275420; color: #FFFFFF; background: none; background-color:#36752D;}
                   div.dhtmlx_window_active, div.dhx_modal_cover_dv { position: fixed !important; }
             
            """
        }
    }
    body {
        h1 {
            mkp.yield "Dependency Report ${new Date()}"
        }
        h3 {
            mkp.yield 'Dependencies that are not up-2-date:'
        }
        div(class:'datagrid'){
            table {
                lines.each { artifact ->
                    if(artifact.length == 1) {
                        tr(class: 'module') {
                            th {
                                mkp.yield artifact[0]
                            }
                            th {
                                mkp.yield 'Current'
                            }
                            th {
                                mkp.yield 'Latest'
                            }
                        }
                    } else {
                        def currentVersion = artifact[2].tokenize('.')
                        def latestVersion = artifact[3].tokenize('.')
                        def severity = 'auto'
                        if(currentVersion[0] != latestVersion[0]){
                            severity = 'red'
                        } else if (currentVersion[2] != latestVersion[2]){
                            severity = 'yellow'
                        }
                        tr {
                            td(class: 'artifact') {
                                a(href: "https://mvnrepository.com/artifact/${artifact[0].trim()}/${artifact[1].trim()}/${artifact[3].trim()}") {
                                    mkp.yield artifact[0] + ':' + artifact[1]
                                }
                            }
                            td(class: 'current', style: "background-color:$severity") {
                                mkp.yield artifact[2]
                            }
                            td(class:'latest') {
                                mkp.yield artifact[3]
                            }
                        }
                    }
                }
            }
        }
    }
}