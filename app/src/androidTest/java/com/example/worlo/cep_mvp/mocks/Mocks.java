package com.example.worlo.cep_mvp.mocks;

/**
 * Created by worlo on 16/07/2017.
 */

public interface Mocks {

    String ERROR = "{\n" +
            "  \"erro\": true\n" +
            "}";

    String SUCCESS_SINGLE_ITEM = "{\n" +
            "  \"cep\": \"32673-136\",\n" +
            "  \"logradouro\": \"Rua Palma de Santa Rita\",\n" +
            "  \"complemento\": \"\",\n" +
            "  \"bairro\": \"Jardim das Alterosas - 2ª Seção\",\n" +
            "  \"localidade\": \"Betim\",\n" +
            "  \"uf\": \"MG\",\n" +
            "  \"unidade\": \"\",\n" +
            "  \"ibge\": \"3106705\",\n" +
            "  \"gia\": \"\"\n" +
            "}";

    String SUCCESS_MULTIPLE_ITEMS = "[\n" +
            "  {\n" +
            "    \"cep\": \"91420-270\",\n" +
            "    \"logradouro\": \"Rua São Domingos\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Bom Jesus\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91040-000\",\n" +
            "    \"logradouro\": \"Rua Domingos Rubbo\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Cristo Redentor\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91040-320\",\n" +
            "    \"logradouro\": \"Rua Domingos Martins\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Cristo Redentor\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91120-090\",\n" +
            "    \"logradouro\": \"Rua Domingos de Abreu\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Sarandi\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91910-450\",\n" +
            "    \"logradouro\": \"Rua Domingos da Silva\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Camaquã\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91360-040\",\n" +
            "    \"logradouro\": \"Rua Domingos Seguézio\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Vila Ipiranga\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91790-072\",\n" +
            "    \"logradouro\": \"Rua Domingos José Poli\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Restinga\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91160-080\",\n" +
            "    \"logradouro\": \"Rua Luiz Domingos Ramos\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Rubem Berta\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"90650-090\",\n" +
            "    \"logradouro\": \"Rua Domingos Crescêncio\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Santana\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91910-420\",\n" +
            "    \"logradouro\": \"Rua José Domingos Varella\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Cavalhada\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91790-101\",\n" +
            "    \"logradouro\": \"Rua Domingos Manoel Mincarone\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Restinga\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"90420-200\",\n" +
            "    \"logradouro\": \"Rua Domingos José de Almeida\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Rio Branco\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91120-480\",\n" +
            "    \"logradouro\": \"Rua Domingos Antônio Santoro\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Sarandi\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91540-650\",\n" +
            "    \"logradouro\": \"Acesso Olavo Domingos de Oliveira\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Jardim Carvalho\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"cep\": \"91740-650\",\n" +
            "    \"logradouro\": \"Praça Domingos Fernandes de Souza\",\n" +
            "    \"complemento\": \"\",\n" +
            "    \"bairro\": \"Cavalhada\",\n" +
            "    \"localidade\": \"Porto Alegre\",\n" +
            "    \"uf\": \"RS\",\n" +
            "    \"unidade\": \"\",\n" +
            "    \"ibge\": \"4314902\",\n" +
            "    \"gia\": \"\"\n" +
            "  }\n" +
            "]";
}
