# Parcial TDSE

**Estudiante:** Laura Natalia Perilla Quintero
## Despliegue paso a paso en AWS EC2

### Clonar y compilar localmente

```bash
git clone https://github.com/TU_USUARIO/active-passive-proxy.git
cd active-passive-proxy

cd math-service
mvn clean package -DskipTests

cd ../proxy-service
mvn clean package -DskipTests
```

dentro de la instancia
```bash
# Actualizar paquetes
sudo yum update -y

sudo yum install java-17-amazon-corretto -y

```

---

### Copiar los JARs a las instancias

Desde tu computador local (en otra terminal, NO dentro de EC2):

```bash
scp -i "C:\Users\laura.perilla-q\Downloads\tdse.pem" C:\Users\laura.perilla-q\Documents\prueba\math-service\target\math-service-1.0.0.jar ec2-user@ec2-52-87-227-129.compute-1.amazonaws.com:/home/ec2-user/ 

scp -i "C:\Users\laura.perilla-q\Downloads\tdse.pem" C:\Users\laura.perilla-q\Documents\prueba\math-service\target\math-service-1.0.0.jar ec2-user@ec2-3-83-46-99.compute-1.amazonaws.com:/home/ec2-user/ 

scp -i "C:\Users\laura.perilla-q\Downloads\tdse.pem" C:\Users\laura.perilla-q\Documents\prueba\proxy-service\target\proxy-service-1.0.0.jar ec2-user@ec2-44-204-241-186.compute-1.amazonaws.com:/home/ec2-user/
---


Coneccion a **EC2**
```bash
ssh -i "tdse.pem" ec2-user@ec2-52-87-227-129.compute-1.amazonaws.com

nohup java -jar math-service-1.0.0.jar > math-service.log 2>&1 &

```

Conectarte a **EC2 #2**:
```bash
ssh -i "tdse.pem" ec2-user@ec2-3-83-46-99.compute-1.amazonaws.com

nohup java -jar math-service-1.0.0.jar > math-service.log 2>&1 &

java -jar math-service-1.0.0.jar --server.port=8081

curl http://localhost:8080/health
```

---

```bash
ssh -i "tdse.pem" ec2-user@ec2-44-204-241-186.compute-1.amazonaws.com


export MATH_SERVICE_1_URL=http://100.24.24.231:8080
export MATH_SERVICE_2_URL=http://35.170.201.46:8080


nohup java -jar proxy-service-1.0.0.jar > proxy.log 2>&1 &
```

---

### Probar el sistema completo
 
```
http://35.173.200.237:8080/
```
Finalmente, esto no se logro ya que la url se quedaba cargando asi las otras dos instancias estuvieran en UP pero el resto de lo solicitado se hizo

### Imagenes 

![img1.png](images%2Fimg1.png)

![img2.png](images%2Fimg2.png)

![img3.png](images%2Fimg3.png)

![img4.png](images%2Fimg4.png)