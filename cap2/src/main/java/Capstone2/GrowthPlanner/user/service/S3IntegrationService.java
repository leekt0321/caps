package Capstone2.GrowthPlanner.user.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.File;
import java.nio.file.Path;

@Service
public class S3IntegrationService {

    @Value("${s3.bucketName}")
    private String bucketName;

    @Value("${s3.region}")
    private String s3Region;

    public void uploadFileToS3(File file, String objectKey) {
        S3Client s3Client = S3Client.builder()
                .region(Region.of(s3Region))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create()) // 환경변수로 설정한 액세스키 자동으로 가져옴.
                .build();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        s3Client.putObject(putObjectRequest, file.toPath());
    }


}


