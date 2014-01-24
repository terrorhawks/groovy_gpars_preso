@Grab(group='org.codehaus.gpars', module='gpars', version='0.12')
import groovyx.gpars.actor.DynamicDispatchActor
        
final class PrimeNumberActor extends DynamicDispatchActor {

    void onMessage(Object number) {
        if (!(2..<number).any { y -> number % y ==0}) {
            println "Prime number ${number}"
        }
    }
}

final def actor = new PrimeNumberActor()
actor.start()
println "Sending messages"
def t = 1..1000
t.each { n ->
    actor.send (n)
}
println "Messages Sent."