package projects.infra.metrics

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Service

@Service
class ProjectMetricsService(
    val meterRegistry: MeterRegistry
) {

    private val projectCreatedCounter: Counter = Counter.builder("projects_created")
        .description("Total number of projects created")
        .tag("operation", "create")
        .register(meterRegistry)


    fun incrementProjectCreated() {
        projectCreatedCounter.increment()
    }

}
