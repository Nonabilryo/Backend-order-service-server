package nonabili.orderserviceserver.client

import nonabili.orderserviceserver.client.dto.response.WriterIdxResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "article-service", url = "\${gateway.server.adress}/article")
interface ArticleClient {
    @GetMapping("/articleIdxToWriterIdx/{articleIdx}")
    fun getWriterIdxByArticleIdx(@PathVariable("articleIdx") articleIdx: String): WriterIdxResponse
}
