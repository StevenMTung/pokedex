![Pokedex_capa](https://github.com/user-attachments/assets/ba2a93aa-ca2d-459f-9363-2630d62ac49b)

<h1>Descrição do projeto</h1>

O Pokédex é um aplicativo que traz informações e detalhes dos 151 Pokémons da primeira geração. Os dados são originados da API gratuíta [PokéAPI](https://pokeapi.co/docs/v2).    


<h1>Funcionalidades</h1>

- `Lista de Pokémons`: É exibido a lista de cards dos 151 Pokémons em uma interface divertida que faz referência a uma Pokébola.
- `Pesquisar Pokémon`: É possível pesquisar o Pokémon desejado digitando seu nome na barra de busca da tela inicial.
- `Detalhes do Pokémon`: Ao realizar o clique em um dos cards da tela inicial é exibido detalhes a respeito do Pokémon selecionado. 

![Pokedex_gif](https://github.com/user-attachments/assets/13fe9c63-d71b-48b9-aa5b-8c14b3b1e2d9)

<h1>Técnicas e tecnologias utilizadas</h1>

O App foi desenvolvido com as seguintes tecnologias:

- `Dagger-Hilt`: Injeção de dependência
- `Jetpack Compose`: Implementação da interface de usuário
- `ViewModel e uiState`: Gerenciamento de Estados de tela
- `Navigation com NavHost`: Navegações entre telas por grafos hospedados em um NavHost
- `Coroutines e Flow`: Rodar as operações de forma assíncrona e reativas
- `Ktor`: Comunicação e requisições a Rest API
- `Modularização`: O projeto foi dividido em multi-módulos por feature e por camadas de data, domain e presenter seguindo as melhores práticas de Clean Architecture
- `Coil`: Upload de imagens e gifs
       
<h1>Acesso ao projeto</h1>

Você pode acessar o [código fonte do projeto](https://github.com/StevenMTung/pokedex) ou [baixá-lo](https://github.com/StevenMTung/pokedex/archive/refs/heads/main.zip).

<h1>Abrir e rodar o projeto</h1> 

Após baixar o projeto, você pode abrir com o `Android Studio`. Para isso, na tela de launcher clique em:

- `Open an Existing Project` (ou alguma opção similar);
- Procure o local onde o projeto está e o selecione (Caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo);
- Por fim clique em `OK`.

O `Android Studio` deve executar algumas tasks do *Gradle* para configurar o projeto, aguarde até finalizar. Ao finalizar as tasks, você pode executar o App 🏆 

<h1>Autor</h1>

 [<img loading="lazy" src="https://avatars.githubusercontent.com/u/134224337?v=4" width=115><br><sub>Steven Marc Tung</sub>](https://github.com/StevenMTung)
| :---: | 
