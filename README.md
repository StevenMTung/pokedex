![Pokedex_capa](https://github.com/user-attachments/assets/ba2a93aa-ca2d-459f-9363-2630d62ac49b)

<h1>Project Description</h1>

The Pokédex is an app that provides information and details about the 151 Pokémon from the first generation. The data is sourced from the free API: [PokéAPI](https://pokeapi.co/docs/v2).    


<h1>Features</h1>

- `Pokémon List`: Displays a list of cards for the 151 Pokémon in a fun interface that references a Poké Ball.
- `Search Pokémon`: You can search for the desired Pokémon by typing its name in the search bar on the home screen.
- `Pokémon Details`: By clicking on one of the cards on the home screen, details about the selected Pokémon are displayed. 

![Pokedex_gif](https://github.com/user-attachments/assets/13fe9c63-d71b-48b9-aa5b-8c14b3b1e2d9)

<h1>Techniques and Technologies Used</h1>

The app was developed with the following technologies:

- `Dagger-Hilt`: Dependency injection
- `Jetpack Compose`: User interface implementation
- `ViewModel e uiState`: Screen state management
- `Navigation com NavHost`: Navigation between screens via graphs hosted in a NavHost
- `Coroutines e Flow`: Running operations asynchronously and reactively
- `Ktor`: Communication and requests to the REST API
- `Modularization`: The project was divided into multi-modules by feature and by layers of data, domain, and presenter following the best practices of Clean Architecture
- `Coil`: Image and GIF upload
       
<h1>Access to the Project</h1>

You can access the [project source code](https://github.com/StevenMTung/pokedex) or [download it](https://github.com/StevenMTung/pokedex/archive/refs/heads/main.zip).

<h1>Open and Run the Project</h1> 

After downloading the project, you can open it with `Android Studio`. To do this, on the launcher screen click on:

- `Open an Existing Project` (or a similar option);
- Find the location where the project is and select it (If the project was downloaded as a zip, you will need to extract it before locating it);
- Finally, click `OK`.

`Android Studio` will execute some Gradle tasks to set up the project, wait for them to finish. Once the tasks are completed, you can run the app 🏆 

<h1>Author</h1>

 [<img loading="lazy" src="https://avatars.githubusercontent.com/u/134224337?v=4" width=115><br><sub>Steven Marc Tung</sub>](https://github.com/StevenMTung)
| :---: | 
