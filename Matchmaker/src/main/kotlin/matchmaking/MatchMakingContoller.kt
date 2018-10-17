package matchmaking

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.util.concurrent.ConcurrentHashMap

@Controller
@RequestMapping(
        path = ["/matchmaking"]
)
class MatchMakingContoller {
    val freegammes: ConcurrentHashMap<String, Int> = ConcurrentHashMap()// id's
    val players_in_game = 4
    @RequestMapping(
            path = ["/join"],
            method = [RequestMethod.POST] ,
            consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    )
    fun join(@RequestParam("name") name: String): ResponseEntity<String> = when {
        name.isEmpty() -> ResponseEntity.badRequest().body("Name is empry")
        name.length > 20 -> ResponseEntity.badRequest().body("Name is too long")
        else -> {
            joinToGame(name)
        }
    }


    fun joinToGame(name: String): ResponseEntity<String> = when {
        freegammes.isEmpty() -> {
                val gameReq = ToServer.create(players_in_game)
                if (gameReq.code == 400)
                    ResponseEntity.badRequest().body("Unable to join to server")
                else {
                    freegammes[gameReq.body!!] = 1
                    ResponseEntity.ok("${gameReq.body}")
                }
            }
        else -> {
            val game = freegammes.keys().nextElement()
            freegammes[game] = freegammes[game]!! + 1
            if (freegammes[game] == players_in_game) {
                freegammes.remove(game)
                ToServer.start(game)
            }
            ResponseEntity.ok().body(game)
        }
    }
}