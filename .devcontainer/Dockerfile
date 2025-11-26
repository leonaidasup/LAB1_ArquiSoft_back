# Dockerfile actualizado
FROM openjdk:17-jdk-slim

# Instalar herramientas necesarias
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends default-mysql-client git maven \
    && apt-get clean -y && rm -rf /var/lib/apt/lists/*

# Crear el usuario vscode
RUN useradd -ms /bin/bash vscode

# Establecer el usuario vscode como el usuario por defecto
USER vscode