# Dependencias
Baixar e instalar o docker desktop

Baixar o grafana, ex: grafana-7.2.0-beta1

Baixar o prometheus, ex: prometheus-2.21.0.windows-amd64

# Configuração
Varrer os diretórios e trocar o IP 192.168.15.25 para o IP da sua máquina

Editar o arquivo prometheus.yml e substituir o scrape_configs pelo trecho abaixo:

scrape_configs:

  - job_name: 'prometheus'
    static_configs:
    - targets: ['localhost:9090']

  - job_name: 'vkanban-issues' 
    scrape_interval: 10s
    metrics_path: 'actuator/prometheus'
    static_configs:
      - targets: ['localhost:8081']

  - job_name: 'vkanban-back' 
    scrape_interval: 10s
    metrics_path: 'actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']

  - job_name: 'config-server' 
    scrape_interval: 10s
    metrics_path: 'actuator/prometheus'
    static_configs:
      - targets: ['localhost:8888']

  - job_name: 'eureka-server' 
    scrape_interval: 10s
    metrics_path: 'actuator/prometheus'
    static_configs:
      - targets: ['localhost:8761'] 

  - job_name: 'zuul-server' 
    scrape_interval: 10s
    metrics_path: 'actuator/prometheus'
    static_configs:
      - targets: ['localhost:8084'] 
      
# Execução

Abrir a workpace no Eclipse e gerar os arquivos .jar pelo maven utilizando o comando 'clean package'

Copiar os .jars que foram gerados nas pastas /target para a pasta docker/files

Ir na pasta 'docker' e executar o comando pelo CMD 'docker-compose up --build'

Após os serviços iniciarem, subir o grafana e o prometheus

Importar o dashboard que está na pasta _files para o Grafana

Importar o projeto postman que está na pasta _files para fazer os testes

# Links

Eureka: http://localhost:8761/

Prometheus: http://localhost:9090/targets

Grafana: http://localhost:3000


