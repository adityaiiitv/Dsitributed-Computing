#include <stdio.h>  
#include <stdlib.h> 
#include <sys/types.h>  
#include <sys/socket.h>  
#include <string.h>
#include <netinet/in.h>

#define PORT 9999
#define BUF_SIZE 2000
#define CLADDR_LEN 100

void main()
{
	struct sockaddr_in addr, cl_addr;
    int sockfd, len, ret, newsockfd;
    char buffer[BUF_SIZE];
    pid_t childpid;
    char clientAddr[CLADDR_LEN];
 	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if(sockfd < 0)
	{
		printf("\n Error creating socket.\n");
		exit(1);
	}
	printf("\n Socket created.\n");
 	memset(&addr, 0, sizeof(addr));

    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = INADDR_ANY;
    addr.sin_port = PORT;

	ret = bind(sockfd, (struct sockaddr *) &addr, sizeof(addr));

	if(ret<0)
	{
		printf("\n Error while binding.\n");
		exit(1);
	}
	printf("\n Binding successful.\n");
	printf("\n Wait for a connection. \n");
	listen(sockfd, 5);
	for(;;)
	{     
		len = sizeof(cl_addr);
  
		newsockfd = accept(sockfd, (struct sockaddr *) &cl_addr, &len);
		if (newsockfd < 0)
		{
			printf("\n Error in accepting connection.\n");
			exit(1);
		}
		printf("\n Connection established.\n");

		inet_ntop(AF_INET, &(cl_addr.sin_addr), clientAddr, CLADDR_LEN);
		if ((childpid = fork()) == 0)
		{ 
			close(sockfd); 
			for(;;)
			{
				memset(buffer, 0, BUF_SIZE);
				ret = recvfrom(newsockfd, buffer, BUF_SIZE, 0, (struct sockaddr *) &cl_addr, &len);
				if(ret < 0)
				{
					printf("\n Error in receiving.\n");  
					exit(1);
				}

				printf("\n Received string from %s: %s\n", clientAddr, buffer); 
				ret = sendto(newsockfd, buffer, BUF_SIZE, 0, (struct sockaddr *) &cl_addr, len);   
				if (ret < 0)
				{  
					printf("\n Error while sending.\n");  
					exit(1);  
				}  
				printf("Sent to %s: %s\n", clientAddr, buffer);
			}
		}
		close(newsockfd);
	}
}
