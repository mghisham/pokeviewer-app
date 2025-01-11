# **PokeDex App**  
A simple app to display Pokémon details using [PokéAPI](https://pokeapi.co/).  

---

## **Features**  
- Displays a list of Pokémon with their names.  
- Tap a Pokémon to view details, including:  
  - Name  
  - Image  
  - Height  
- Clean and modern Material Design.  

---

## **Tech Stack**  
- **Kotlin**  
- **Jetpack Compose** for UI  
- **Retrofit** for network calls  
- **Hilt** for dependency injection  
- **Coil (Coil-Compose)** for image loading  
- **ViewModel** for state management  

---

## **Architecture**  
The app uses a **multi-module clean architecture**, segregated into the following modules:  
1. **Data**: Handles API calls, repository implementation, and data sources.  
2. **Domain**: Contains use cases and defines the core business logic.  
3. **UI**: Manages user interface and composables.  

Follows the **MVVM (Model-View-ViewModel)** pattern:  
- **Model**: Handles API responses and data transformations.  
- **ViewModel**: Manages app data and logic.  
- **View**: Composables render the UI.  

---

## **Testing**  
### **Unit Testing**  
- Tests are written for:
  - **UI Layer**: ViewModel classes
  - **Domain Layer**: Use case classes.  
  - **Data Layer**: Repository implementation.  
- Uses JUnit and Mockk for mocking dependencies.  

### **UI Testing**  
- **Espresso** tests are written for UI screen rendering.  

---

## **Setup**  
1. Clone the repository:  
   ```bash  
   git clone https://github.com/mghisham/pokeviewer-app.git  
   ```  
2. Open in Android Studio.  
3. Sync Gradle files.  
4. Run on an emulator or device.  

---

## **API Reference**  
- List Pokémon: `https://pokeapi.co/api/v2/pokemon`  
- Pokémon Details: `https://pokeapi.co/api/v2/pokemon/{id}`  

---

## **References**  
- Launcher icon sourced from [Google Fonts Icons](https://fonts.google.com/icons).  

