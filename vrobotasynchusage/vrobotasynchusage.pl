%====================================================================================
% vrobotasynchusage description   
%====================================================================================
request( step, step(TIME) ).
reply( stepdone, stepdone(V) ).  %%for step
reply( stepfailed, stepfailed(DURATION,CAUSE) ).  %%for step
event( sonardata, sonar(DISTANCE) ).
%====================================================================================
context(ctxvrobotusage, "localhost",  "TCP", "8080").
 qactor( perimeterasynch, ctxvrobotusage, "it.unibo.perimeterasynch.Perimeterasynch").
 static(perimeterasynch).
