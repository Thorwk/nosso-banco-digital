package com.zup.bootcamp.nossobancodigital.service;

import com.zup.bootcamp.nossobancodigital.entity.ClientEntity;
import com.zup.bootcamp.nossobancodigital.repository.ClientRepository;
import com.zup.bootcamp.nossobancodigital.properties.FileUploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.NoSuchElementException;

@Service
public class FileService {

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
    public void init() throws Exception{
        try{
            Files.createDirectories(this.dirLocation);
        }catch(Exception e){
            throw new FileSystemException("Não foi possível criar o diretório de upload!");
        }
    }

    public String saveFile(MultipartFile file, String id) throws FileSystemException {
        verifyStep(id, 2);
        try{
            ClientEntity client = clientRepository.findById(id).get();

            String fileName = client.getCpf() + " - " + file.getOriginalFilename();
            Path dFile = this.dirLocation.resolve(fileName);
            Files.copy(file.getInputStream(), dFile, StandardCopyOption.REPLACE_EXISTING);

            client.setArquivoCPF(fileName);
            client.setEtapa(3);
            clientRepository.save(client);
        }catch (Exception e) {
            throw new FileSystemException("Não foi possível fazer o upload do arquivo!");
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
            throw new EntityNotFoundException("Complete os passos anteriores!");
        }
    }

    public void verifyClient(String id) throws NoSuchElementException {
        if(!clientRepository.existsById(id)){
            throw new NoSuchElementException("Cliente não cadastrado");
        }
    }

}
