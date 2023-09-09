package com.ufes.encomp.coffeebreakpass.rest

import io.github.g0dkar.qrcode.QRCode
import io.github.g0dkar.qrcode.render.Colors
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders.CONTENT_DISPOSITION
import org.springframework.http.MediaType.IMAGE_PNG_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayOutputStream

@Controller
class QRCodeGenerator {
    @GetMapping("/qrcode")
    fun generateQrCode(): ResponseEntity<ByteArrayResource> {
        val imageOut = ByteArrayOutputStream()

        val content = "https://mapcom.ufes.br/"

        QRCode(content).render(
            brightColor = Colors.BLACK,
            darkColor = Colors.WHITE
        ).writeImage(imageOut)

        val imageBytes = imageOut.toByteArray()
        val resource = ByteArrayResource(imageBytes, IMAGE_PNG_VALUE)

        return ResponseEntity.ok()
            .header(CONTENT_DISPOSITION, "attachment; filename=\"qrcode.png\"")
            .body(resource)
    }
}