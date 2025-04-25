%====================================================================================
% sonar24qak description   
%====================================================================================
dispatch( sonarstart, sonarstart(X) ).
dispatch( sonarstop, sonarstop(X) ).
event( sonardata, distance(V) ).
%====================================================================================
context(ctxsonarqak24, "localhost",  "TCP", "8080").
 qactor( sonardevice, ctxsonarqak24, "it.unibo.sonardevice.Sonardevice").
 static(sonardevice).
  qactor( datacleaner, ctxsonarqak24, "it.unibo.datacleaner.Datacleaner").
 static(datacleaner).
  qactor( sonar24, ctxsonarqak24, "it.unibo.sonar24.Sonar24").
 static(sonar24).
  qactor( sonarusagemock, ctxsonarqak24, "it.unibo.sonarusagemock.Sonarusagemock").
 static(sonarusagemock).
