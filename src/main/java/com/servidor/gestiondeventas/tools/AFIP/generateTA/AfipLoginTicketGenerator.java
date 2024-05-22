package com.servidor.gestiondeventas.tools.AFIP.generateTA;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class AfipLoginTicketGenerator {
    public String generateLoginTicketRequest() {
        try {
            boolean production = false;
            String clavePrivada;
            String certificado;
            if(production){
                    clavePrivada = "clave_privada.pem";
                    certificado = "BenferCompany_43df168512b90b55.crt";
            }else{
                clavePrivada = "privatehomologate.key";
                certificado = "autorizehomologate.pem";
            }
            Security.addProvider(new BouncyCastleProvider());

            // Cargar la clave privada desde un archivo
            ClassPathResource privateKeyResource = new ClassPathResource(clavePrivada);
            FileInputStream privateKeyStream = new FileInputStream(privateKeyResource.getFile());
            byte[] privateKeyBytes = privateKeyStream.readAllBytes();
            PrivateKey privateKey = CryptoUtils.getPrivateKeyFromPEM(privateKeyBytes);

            // Cargar el certificado desde un archivo
            ClassPathResource certResource = new ClassPathResource(certificado);
            FileInputStream certStream = new FileInputStream(certResource.getFile());
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(certStream);

            // Construir el XML del ticket de login
            String loginTicketRequestXml = buildLoginTicketRequestXml();

            // Firmar el XML
            byte[] signedXml = CryptoUtils.signXml(loginTicketRequestXml.getBytes("UTF-8"), privateKey, cert);

            // Codificar el XML firmado en base64
            String base64EncodedXml = Base64.getEncoder().encodeToString(signedXml);

            return base64EncodedXml;
        } catch (Exception e) {
            e.printStackTrace();
            return "algo salio mal";
        }
    }

    private String buildLoginTicketRequestXml() {
        LocalDateTime currentTime = LocalDateTime.now();

        // Agrega 5 minutos a la fecha y hora actual
        LocalDateTime expirationTime = currentTime.plusSeconds(60);

        // Formatea las fechas y horas en el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String generationTimeFormatted = currentTime.format(formatter);
        String expirationTimeFormatted = expirationTime.format(formatter);

        // Construye el XML utilizando las fechas formateadas
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<loginTicketRequest version=\"1.0\">\n" +
                "    <header>\n" +
                "        <uniqueId>1265411</uniqueId>\n" +
                "        <generationTime>" + generationTimeFormatted + "</generationTime>\n" +
                "        <expirationTime>" + expirationTimeFormatted + "</expirationTime>\n" +
                "    </header>\n" +
                "    <service>wsfe</service>\n" +
                "</loginTicketRequest>";
    }
}

