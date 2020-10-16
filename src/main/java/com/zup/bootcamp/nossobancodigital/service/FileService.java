package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.NossoBancoDigitalApplication;
import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.repository.ClientRepository;
import com.zup.bootcamp.nossobancodigital.properties.FileUploadProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.NoSuchElementException;

@Service
public class FileService {

    private static Logger logger = LoggerFactory.getLogger(NossoBancoDigitalApplication.class);

    private final Path dirLocation;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    public FileService(FileUploadProperties fileUploadProperties) {
        this.dirLocation = Paths.get(fileUploadProperties.getLocation())
                                .toAbsolutePath()
                                .normalize();
    }

    @PostConstruct
    public void init() throws IOException{
        try{
            Files.createDirectories(this.dirLocation);
        }catch(IOException e){
            throw new IOException("Não foi possível criar o diretório de upload!");
        }
    }

    public String saveFile(MultipartFile file, String id) throws IOException {
        verifyStep(id, 2);
        try{
            ClientEntity client = clientRepository.findById(id).get();

            String fileName = client.getCpf() + " - " + file.getOriginalFilename();
            Path dFile = this.dirLocation.resolve(fileName);
            Files.copy(file.getInputStream(), dFile, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Imagem salva no diretório de uploads");

            client.setArquivoCPF(fileName);
            client.setEtapa(3);
            clientRepository.save(client);
        }catch (IOException e) {
            logger.info("Upload não realizado");
            throw new IOException("Não foi possível fazer o upload do arquivo!");
        }

        String location = ServletUriComponentsBuilder
                .fromUriString("http://localhost:8080/")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();

        return location;

    }

    public Resource loadFile(String id) throws FileNotFoundException {
        verifyClient(id);
        String fileName = clientRepository.findById(id).get().getArquivoCPF();

        try {
            Path file = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Não foi possível encontrar o arquivo!");
            }
        }catch (MalformedURLException e){
            throw new FileNotFoundException("Não foi possível fazer o download do arquivo!");
        }

    }

    public void verifyStep(String id, int etapa) throws EntityNotFoundException {
        if(clientRepository.findById(id).get().getEtapa() < etapa){
            logger.info("Etapa anterior não foi completada");
            throw new EntityNotFoundException("Complete os passos anteriores!");
        }
    }

    public void verifyClient(String id) throws NoSuchElementException {
        if(!clientRepository.existsById(id)){
            logger.info("Cliente não encontrado");
            throw new NoSuchElementException("Cliente não cadastrado");
        }
    }

}
