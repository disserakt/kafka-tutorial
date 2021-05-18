plugins {
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:2.8.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.3")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("com.google.guava:guava:29.0-jre")
}

application {
    mainClass.set("ru.netology.App")
}

tasks.test {
    useJUnitPlatform()
}
