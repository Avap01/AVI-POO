package br.com.mariojp.parsers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Define a política de retenção da anotação como RUNTIME, o que significa que ela estará disponível em tempo de execução
@Target(ElementType.TYPE) // Define que a anotação pode ser aplicada a tipos (classes, interfaces, enumerações)

public @interface ExibirData { // Declaração da anotação ExibirData
	String getDataFormada(); // Método dentro da anotação para obter a data formatada
}
