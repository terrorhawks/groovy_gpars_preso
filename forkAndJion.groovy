@Grab(group='org.codehaus.gpars', module='gpars', version='0.12')
import static groovyx.gpars.GParsPool.runForkJoin
import static groovyx.gpars.GParsPool.withPool

def start = System.currentTimeMillis() 
//feel free to experiment with the number of fork/join threads in the pool
withPool(1) {pool ->
    
    def fileCount = runForkJoin(new File("/usr/local/")) {file ->
            long count = 0
            file.eachFile {
                if (it.isDirectory()) {
                    println "Forking a child task for $it"
                    forkOffChild(it)       //fork a child task
                } else {
                    count++
                }
            }
            return count + (childrenResults.sum(0))
            //use results of children tasks to calculate and store own result
        }
    println "Number of files: ${fileCount}"
}
def now = System.currentTimeMillis() 
println "Time in ${ (now - start)} ms"
