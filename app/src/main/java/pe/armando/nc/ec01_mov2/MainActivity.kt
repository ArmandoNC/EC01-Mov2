package pe.armando.nc.ec01_mov2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.armando.nc.ec01_mov2.ui.theme.Ec01mov2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ec01mov2Theme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    var currentScreen by remember { mutableStateOf("menuPrincipal") }

    when (currentScreen) {
        "menuPrincipal" -> MenuPrincipal(onNavigate = { screen -> currentScreen = screen })
        "ejercicio1" -> Ejercicio1(onBack = { currentScreen = "menuPrincipal" })
        "ejercicio2" -> Ejercicio2(onBack = { currentScreen = "menuPrincipal" })
        "ejercicio3" -> Ejercicio3(onBack = { currentScreen = "menuPrincipal" })
        "ejercicio4" -> Ejercicio4(onBack = { currentScreen = "menuPrincipal" })
    }
}

@Composable
fun MenuPrincipal(onNavigate: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { onNavigate("ejercicio1") }) {
                Text(text = "Ejercicio 1")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onNavigate("ejercicio2") }) {
                Text(text = "Ejercicio 2")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onNavigate("ejercicio3") }) {
                Text(text = "Ejercicio 3")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onNavigate("ejercicio4") }) {
                Text(text = "Ejercicio 4")
            }
        }
    }
}

@Composable
fun Ejercicio1(onBack: () -> Unit) {
    var indicador by remember { mutableStateOf("") }
    var tasaAlcohol by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val tasasMaximas = mapOf(
        "C" to 0.3,
        "A" to 0.3,
        "T" to 0.5,
        "M" to 0.3
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Control de Alcoholemia", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = indicador,
            onValueChange = { indicador = it },
            label = { Text(text = "Indicador de Vehículo (C, A, T, M)") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = tasaAlcohol,
            onValueChange = { tasaAlcohol = it },
            label = { Text(text = "Tasa de Alcohol") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val tasa = tasaAlcohol.toDoubleOrNull()
            if (tasa != null && tasasMaximas.containsKey(indicador)) {
                val tasaMaxima = tasasMaximas[indicador]!!
                resultado = if (tasa <= tasaMaxima) "Negativo" else "Positivo"
            } else {
                resultado = "Datos inválidos"
            }
        }) {
            Text(text = "Verificar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = resultado)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Volver")
        }
    }
}

@Composable
fun Ejercicio2(onBack: () -> Unit) {
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ingrese dos números")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text(text = "Número 1") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text(text = "Número 2") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val num1 = numero1.toDoubleOrNull()
            val num2 = numero2.toDoubleOrNull()

            if (num1 != null && num2 != null) {
                resultado = when {
                    num1 == num2 -> (num1 * num2).toString()
                    num1 > num2 -> (num1 - num2).toString()
                    else -> (num1 + num2).toString()
                }
            } else {
                resultado = "Datos inválidos"
            }
        }) {
            Text(text = "Calcular")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Resultado: $resultado")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Volver")
        }
    }
}

@Composable
fun Ejercicio3(onBack: () -> Unit) {
    var numeroMes by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val meses = arrayOf(
        "enero", "febrero", "marzo", "abril", "mayo", "junio",
        "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ingrese un número del 1 al 12")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = numeroMes,
            onValueChange = { numeroMes = it },
            label = { Text(text = "Número de mes") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val numMes = numeroMes.toIntOrNull()
            if (numMes != null && numMes in 1..12) {
                resultado = meses[numMes - 1].capitalize()
            } else {
                resultado = "Mes no encontrado"
            }
        }) {
            Text(text = "Verificar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = resultado)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Volver")
        }
    }
}

@Composable
fun Ejercicio4(onBack: () -> Unit) {
    var resultado by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Suma de dígitos entre 23 y 99")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            var suma = 0
            for (i in 23..99) {
                val numStr = i.toString()
                for (char in numStr) {
                    suma += char.toString().toInt()
                }
            }
            resultado = suma
        }) {
            Text(text = "Calcular")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Suma de dígitos: $resultado")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text(text = "Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPrincipalPreview() {
    Ec01mov2Theme {
        MenuPrincipal(onNavigate = {})
    }
}