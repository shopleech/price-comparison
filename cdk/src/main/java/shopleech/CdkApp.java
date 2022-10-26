package shopleech;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public final class CdkApp {
    public static void main(final String[] args) {
        App app = new App();

        StackProps pr =
                new StackProps.Builder()
                        .env(
                                Environment.builder()
                                        .region("eu-west-1")
                                        .account(System.getenv("CDK_DEFAULT_ACCOUNT"))
                                        .build())
                        .build();

        new CdkStack(app, "CdkStack", pr);

        app.synth();
    }
}
