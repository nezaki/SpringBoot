# SpringBoot

* 初期構築
```
chmod +x ./gradlew
./gradlew build
docker-compose up -d
./gradlew flywayMigrate
```

* アプリケーション起動
```
./gradlew bootRun
```

* jarの作成〜起動
```
./gradlew clean bootJar
java -jar build/libs/dmb-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```
