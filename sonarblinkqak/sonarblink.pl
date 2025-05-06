%====================================================================================
% sonarblink description   
%====================================================================================
event( sonardata, distance(V) ).
event( ledcmd, state(S) ).
%====================================================================================
context(ctxsonarblink, "localhost",  "TCP", "8080").
 qactor( sonardevice, ctxsonarblink, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
  qactor( mastermind, ctxsonarblink, "it.unibo.mastermind.Mastermind").
 static(mastermind).
  qactor( leddevice, ctxsonarblink, "it.unibo.leddevice.Leddevice").
 static(leddevice).
