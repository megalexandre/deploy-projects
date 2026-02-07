package projects

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DeployBoardApplication

fun main(args: Array<String>) {
	runApplication<DeployBoardApplication>(*args)
}
