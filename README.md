# issLab25
Repo for the "Ingegneria dei Sistemi Software M" course at UNIBO

<h2>Fase 1</h2>

### Primi microservizi realizzati in Java
  * [myConwayGui](myConwayGui): Prima realizzazione monolitica di una GUI per Il gioco della vita di Conway quale servizio SpringBoot
  * [conway_mmi](conway_mmi): Applicativo di interazione Machine-to-Machine con il microservizio [conway25Java](https://github.com/anatali/issLab2025.git), disponibile sul repository del corso issLab25
  * [conwaygui_mqtt](conwaygui_mqtt): Tentativo per microservizio standalone che fornisce una GUI per Il gioco della vita di Conway usando solo interazioni basate sul protocollo MQTT comunicando con una versione modificata del microservizio [conway25JavaMqtt](https://github.com/anatali/issLab2025.git), disponibile sul repository del corso issLab25, che verrà pubblicata a breve

<h2>Fase 2</h2>

### Primi agenti situati modellati tramite il DSL Qak
  * [sonarqak24](sonarqak24): Componente software, sviluppato tramite linguaggio custom qak, che utilizza il SONAR HC-SR04 in grado di ricevere comandi di sonarstart/sonarstop e inviare ad altri componenti software i valori di distanza rilevati, filtrati in modo che ogni valore D emesso sia un valore intero tale che 0<D<=150
  * [sonarblinkqak](sonarblinkqak): sistema basato su un sonar e un led che, prefissate due distanze D1 e D2 con 0 < D2 < D1, utilizza un sonar per rilevare una distanza D:
    - se D > D1: il led deve essere spento
    - se D < D1 e D > D2 :   il led blinks
    - se D < D2 : il led rimane acceso
  * [vrobotusage](vrobotusage): componente software che governa un robot virtuale. Al momento l'unica funzionalità disponibile è il movimento lungo il perimetro di una stanza, temporaneamente arrestato qualora il robot transiti dinanzi a un sonar 
  * [vrobotasynchusage](vrobotasynchusage): microservizio che invia comandi a un robot virtuale
