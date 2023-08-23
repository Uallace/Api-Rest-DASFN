package br.com.desafio.model.response;


import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;


@Data
public class Root implements Serializable {

    private static final long serialVersionUID = 1L;
    public ArrayList<TaxaMensalResponse> value;
}
