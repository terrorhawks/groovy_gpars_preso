@Grab(group='org.codehaus.gpars', module='gpars', version='0.12')
import groovyx.gpars.actor.DynamicDispatchActor
        
final class PrimeNumberActor extends DynamicDispatchActor {

    void onMessage(Object number) {
        prime(number)
    }
    
    static void prime(number) {
        if (!(2..<number).any { y -> number % y ==0}) print "${number} "        
    }
    
}

def benchmark = { closure ->  
  start = System.currentTimeMillis()  
  closure.call()  
  now = System.currentTimeMillis()  
  now - start  
}

def durationSeq = benchmark {
    (1..1000).each { n -> PrimeNumberActor.prime(n) }
}
println "\nExecution time sequential in ${durationSeq} ms"


final def actor = new PrimeNumberActor()
actor.start()

Actors.actor {
	def durationActor = benchmark { 
	   	(1..1000).each { n -> actor.send (n) }
		actor.join()
	}
}.join()


println "\nExecution time actors in ${durationActor} ms"



