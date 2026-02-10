package projects.commons
import com.github.f4b6a3.uuid.UuidCreator

class Id {

    companion object {
        fun random(): String = UuidCreator.getTimeOrderedEpoch().toString()
    }

}