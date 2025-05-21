%====================================================================================
% vrobotusage description   
%====================================================================================
dispatch( stepdone, stepdone(v) ).
dispatch( stepfailed, stepfailed(v) ).
event( sonardata, sonar(d) ).
%====================================================================================
context(ctxvrobotusage, "localhost",  "TCP", "8080").
 qactor( perimeter, ctxvrobotusage, "it.unibo.perimeter.Perimeter").
 static(perimeter).
