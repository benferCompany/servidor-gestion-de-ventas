package com.servidor.gestiondeventas.tools.AFIP.generateTA;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class CryptoUtils {
    public static PrivateKey getPrivateKeyFromPEM(byte[] privateKeyBytes) throws IOException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PEMParser pemParser = new PEMParser(new StringReader(new String(privateKeyBytes)));
        Object object = pemParser.readObject();
        pemParser.close();

        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        return converter.getPrivateKey((PrivateKeyInfo) object);
    }
    public static byte[] signXml(byte[] data, PrivateKey privateKey, X509Certificate cert) throws CMSException, IOException, OperatorCreationException, CertificateEncodingException {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
            ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256withRSA").build(privateKey);
            generator.addSignerInfoGenerator(new JcaSimpleSignerInfoGeneratorBuilder().build("SHA256withRSA", privateKey, cert));
            generator.addCertificate(new X509CertificateHolder(cert.getEncoded()));
            CMSTypedData content = new CMSProcessableByteArray(data);
            CMSSignedData signedData = generator.generate(content, true);

            return signedData.getEncoded();
        } catch (CertificateEncodingException | OperatorCreationException | CMSException e) {
            throw new RuntimeException("Error al firmar el XML", e);
        }
    }
}
