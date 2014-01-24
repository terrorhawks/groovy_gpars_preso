class PrimeNumberActor {
    static void prime(number) {
        if (!(2..<number).any { y -> number % y ==0}) print "${number} "        
    }    
}

start = System.currentTimeMillis()  
(1..1000).each { n -> PrimeNumberActor.prime(n) }
now = System.currentTimeMillis()  
println "\nExecution time in ${now - start} ms"  



