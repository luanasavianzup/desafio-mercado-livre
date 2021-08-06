package br.com.zup.luanasavian.mercadolivre.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemProdutoFormRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens = new ArrayList<>();

   /* public ImagemProdutoFormRequest(@Size(min = 1) List<MultipartFile> imagens) {
        this.imagens = imagens;
    } */

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
