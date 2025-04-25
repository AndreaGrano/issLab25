### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('sonar24qakArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxsonarqak24', graph_attr=nodeattr):
          sonardevice=Custom('sonardevice','./qakicons/symActorWithobjSmall.png')
          datacleaner=Custom('datacleaner','./qakicons/symActorWithobjSmall.png')
          sonar24=Custom('sonar24','./qakicons/symActorWithobjSmall.png')
          sonarusagemock=Custom('sonarusagemock','./qakicons/symActorWithobjSmall.png')
     sonardevice >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> datacleaner
     datacleaner >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> sonar24
     sonar24 >> Edge(color='blue', style='solid',  decorate='true', label='<sonarstart &nbsp; sonarstop &nbsp; >',  fontcolor='blue') >> sonardevice
     sonarusagemock >> Edge(color='blue', style='solid',  decorate='true', label='<sonarstart &nbsp; sonarstop &nbsp; >',  fontcolor='blue') >> sonar24
diag
