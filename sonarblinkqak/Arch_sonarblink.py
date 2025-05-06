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
with Diagram('sonarblinkArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxsonarblink', graph_attr=nodeattr):
          sonardevice=Custom('sonardevice','./qakicons/symActorWithobjSmall.png')
          mastermind=Custom('mastermind','./qakicons/symActorWithobjSmall.png')
          leddevice=Custom('leddevice','./qakicons/symActorWithobjSmall.png')
     sonardevice >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> mastermind
     mastermind >> Edge( label='ledcmd', **eventedgeattr, decorate='true', fontcolor='red') >> leddevice
diag
