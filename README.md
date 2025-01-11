# **PokeViewer App**  
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
## **Screenshots**

|# |List Loading|List Loaded|Details Screen|
|---|---|---|---|
|![image](https://github.com/user-attachments/assets/811329d5-109a-4475-aa06-041f3346a906)|![image](https://github.com/user-attachments/assets/16e0d977-69d3-4cfc-9f2f-fd74c79d6f5c)|![image](https://github.com/user-attachments/assets/657b5ead-39b3-4e83-b505-125f5a55216b)|![image](https://github.com/user-attachments/assets/b5777284-047a-4ef7-aa19-d6f54f92c3ac)|
|![image](https://github.com/user-attachments/assets/6a7f6e46-3cc2-4908-a451-c64f2e2d91be)|![image](https://github.com/user-attachments/assets/452a7a8c-8f3c-4260-82bf-62d14eb8837f)|![image](https://github.com/user-attachments/assets/f228e0f6-1d45-4911-8a9d-a594d2db7eff)|![image](https://github.com/user-attachments/assets/efc9159d-65d4-4ec7-8035-6c98ba78219f)|


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

