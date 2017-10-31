# Cep_MVP

<img src="/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" align="left" width="200" hspace="10" vspace="10">

[![](https://play.google.com/intl/en_us/badges/images/badge_new.png)](https://play.google.com/store/apps/details?id=dmobile.example.worlo.cep_mvp&hl=pt_BR)

Aplicativo android para localização de cep e endereços baseado na arquitetura MVP e na API na api ViaCep: https://viacep.com.br/

## Começando

Estas instruções devem lhe guiar como instalar o aplicativo, qual versão da plataforma android necessária e como foi desenvolvido a aplicação.

### Pré-requisitos

Para instalação em um dispositivo este deve possuir no mínimo a versão 15 (Ice Cream Sandwich) do sistema android. Para navegar e alterar o código fonte é recomendado a IDE Android Studio com acesso a internet.

### Capturas de telas

[![](https://lh3.googleusercontent.com/wN4Fh199UoeQ2m_xS1RPgwYG2Cga5UQnmIIfsIXhSJR-IDnKxem3d1rmAP5kTtb1Uki6=h500-rw)](https://play.google.com/store/apps/details?id=dmobile.example.worlo.cep_mvp&hl=pt_BR) [![](https://lh3.googleusercontent.com/NDEVh0CiA2aLI9-t78Br8zT7Y1uqUk37GdRNkGOCsgqHSEMYaIZLcnzNqLiJBAij4w=h500-rw)](https://play.google.com/store/apps/details?id=dmobile.example.worlo.cep_mvp&hl=pt_BR)
[![](https://lh3.googleusercontent.com/GF6OzS46ROJKuE4l0tHp3Z2dxKCd6P6k9vZForm66BMdMGj9_ZusOaFnv_6L81RCok8=h500-rw)](https://play.google.com/store/apps/details?id=dmobile.example.worlo.cep_mvp&hl=pt_BR)

### Recursos

Pesquisa por cep

```
Encontrar dados do endereço a partir do cep digitado.
```

Pesquisa dados da localidade

```
Encontrar dados do endereço a partir do estado, cidade e logradouro.
```

## Executando os testes

Teste de interface se encontra no pacote androidTest

* [Testes de Interface](https://github.com/worlock257/Cep_MVP/tree/master/app/src/androidTest/java/com/example/worlo/cep_mvp)

### O que cobrem os testes?

O teste de interface cobre todo o funcionamento básico do sistema.

```
Pesquisa por cep

Pesquisa por dados do endereço
```

## Construído com

* [Butter Knife](http://jakewharton.github.io/butterknife/) - Biblioteca de injeção de view
* [Retrofit](http://square.github.io/retrofit/) - Cliente HTTP
* [GSON](https://github.com/google/gson) - Converter objetos java para o formato JSON ou vice-versa.
* [Dagger2](https://google.github.io/dagger/) - Injeção de depedência
* [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) - Testes de UI.

## Autor

* **Douglas Maciel** - *Trabalho inicial* - [worlock257](https://github.com/worlock257)

