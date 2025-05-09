/* Generated by AN DISI Unibo */ 
package it.unibo.mastermind

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023
//Sept2024
import org.slf4j.Logger
import org.slf4j.LoggerFactory 
import org.json.simple.parser.JSONParser
import org.json.simple.JSONObject


//User imports JAN2024

class Mastermind ( name: String, scope: CoroutineScope, isconfined: Boolean=false, isdynamic: Boolean=false ) : 
          ActorBasicFsm( name, scope, confined=isconfined, dynamically=isdynamic ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		//IF actor.withobj !== null val actor.withobj.name» = actor.withobj.method»ENDIF
		
				val minD = 8
				val maxD = 16
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						subscribeToLocalActor("sonardevice") 
						CommUtils.outgreen("$name: subscribed to sonardevice for sonardata")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="waitValue", cond=doswitch() )
				}	 
				state("waitValue") { //this:State
					action { //it:State
						CommUtils.outgreen("$name: waiting...")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="elabMsg",cond=whenEvent("sonardata"))
				}	 
				state("elabMsg") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("distance(V)"), Term.createTerm("distance(V)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 val V = payloadArg(0).toDouble()  
								if(  V <= minD  
								 ){emitlocal("ledcmd", "state(on)" ) 
								CommUtils.outgreen("$name: led on")
								}
								if(  V >= maxD  
								 ){emitlocal("ledcmd", "state(off)" ) 
								CommUtils.outgreen("$name: led off")
								}
								if(  V > minD && V < maxD  
								 ){emitlocal("ledcmd", "state(blink)" ) 
								CommUtils.outgreen("$name: led blink")
								}
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="waitValue", cond=doswitch() )
				}	 
			}
		}
} 
