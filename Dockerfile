# ==========================================
# GIAI ĐOẠN 1: BUILD CODE BẰNG JDK
# ==========================================
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /build

# Copy các file cấu hình Maven vào trước
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Copy toàn bộ mã nguồn Java vào
COPY src/ src/

# Tự động build ra file .jar ngay bên trong Docker!
RUN ./mvnw clean package -DskipTests

# ==========================================
# GIAI ĐOẠN 2: CHẠY ỨNG DỤNG BẰNG JRE SIÊU NHẸ
# ==========================================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Điều kỳ diệu: Copy file .jar TỪ GIAI ĐOẠN 1 (builder) sang giai đoạn 2
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]