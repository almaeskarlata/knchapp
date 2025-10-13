package com.ja.kanchap

import android.content.Intent // Necesario para iniciar otra Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log // Para logging
import com.google.firebase.auth.FirebaseAuth // Importa Firebase Auth
import com.google.firebase.auth.ktx.auth // Extensiones KTX
import com.google.firebase.ktx.Firebase // Para inicializar Firebase

class AuthActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth // Instancia de Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main) // Quita o comenta esta línea por ahora

        // Inicializa Firebase Auth
        auth = Firebase.auth

        // === Verificar el estado de autenticación del usuario ===
        checkAuthenticationState()

    }

    private fun checkAuthenticationState() {
        val currentUser = auth.currentUser // Obtiene el usuario actualmente logueado

        if (currentUser == null) {
            // No hay usuario logueado, redirigir a AuthActivity
            Log.d("MainActivity", "No user is logged in. Redirecting to AuthActivity.")
            navigateToAuthActivity()
        } else {
            // Hay un usuario logueado
            Log.d("MainActivity", "User ${currentUser.uid} is logged in.")
            // Aquí es donde cargarías el contenido principal de MainActivity
            // Por ahora, solo mostramos un mensaje o el layout básico si tienes uno.
            // Ya que la app principal aún no está construida, simplemente nos quedamos aquí.
            // En una app real, cargarías tus Fragments/UI principales aquí.
            setContentView(R.layout.activity_main) // Puedes inflar tu layout principal aquí
            // Ejemplo: binding = ActivityMainBinding.inflate(layoutInflater); setContentView(binding.root)
            // ... cargar fragments principales o configurar la UI
        }
    }

    private fun navigateToAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        // Añadir flags para limpiar el back stack es opcional aquí,
        // ya que AuthActivity limpiará al ir a MainActivity.
        // Pero es buena práctica si AuthActivity pudiera navegar a otras pantallas antes del login exitoso.
        // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Cierra MainActivity para que el usuario no pueda volver presionando Atrás
    }

    // Opcional: Puedes tener un método para cerrar sesión y volver a AuthActivity
    fun signOut() {
        auth.signOut()
        Log.d("MainActivity", "User signed out. Redirecting to AuthActivity.")
        navigateToAuthActivity()
    }

    // Si tienes un menú u otro UI en MainActivity, puedes añadir un botón de cerrar sesión
    // y llamar a signOut() desde su OnClickListener.

}
