FROM public.ecr.aws/lambda/java:11

COPY build/classes/java/main ${LAMBDA_TASK_ROOT}
COPY build/dependencies/* ${LAMBDA_TASK_ROOT}/lib/

CMD [ "com.shopleech.App::handleRequest" ]