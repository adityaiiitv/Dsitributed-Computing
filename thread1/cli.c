#include <stdio.h>  
#include <stdlib.h> 
#include <sys/types.h>  
#include <sys/socket.h>  
#include <string.h>
#include <netinet/in.h>
#include <netdb.h>
  
#define PORT 9999
#define BUF_SIZE 2000 
  

int main(int argc, char**argv)
{  

	struct sockaddr_in addr, cl_addr;  
    int sockfd, ret;  
    char buffer[BUF_SIZE];  
    struct hostent * server;
    char * serverAddr;


	if(argc < 2)
	{
		printf("\n Enter ./cli <IP address> in the terminal.\n");
		exit(1);  
	}

	serverAddr = argv[1]; 
 
	sockfd = socket(AF_INET, SOCK_STREAM, 0); 

	if(sockfd < 0)
	{  
		printf("\n Error while creating a socket. \n");  
		exit(1);  
	}  

    printf("Socket created.\n");   

	memset(&addr, 0, sizeof(addr));

    addr.sin_family = AF_INET;  
    addr.sin_addr.s_addr = inet_addr(serverAddr);
    addr.sin_port = PORT;     

	ret = connect(sockfd, (struct sockaddr *) &addr, sizeof(addr));


	if (ret < 0)
	{  
		printf("\n Error in connection.\n");  
		exit(1);  
	}  
	printf("\n Connected.\n");

	memset(buffer, 0, BUF_SIZE);
	printf("\n Enter the string: ");

	while (fgets(buffer, BUF_SIZE, stdin) != NULL) 
	{
		ret = sendto(sockfd, buffer, BUF_SIZE, 0, (struct sockaddr *) &addr, sizeof(addr));   
		if (ret < 0)
		{  
			printf("\n Error while sending.\n");  
		}
		ret = recvfrom(sockfd, buffer, BUF_SIZE, 0, NULL, NULL);  
 
		if (ret < 0)
		{  
			printf("\n Error while receiving.\n");    
		} 
		else
		{
			printf("\n String reversed: ");
			//
			int l=strlen(buffer),i=0,j=0;
			char new1[l+1];new1[l]='\0';
			for(i=l-1;i>=0;i--)
			{
				new1[j]=buffer[i];j++;
			}

			//
			fputs(new1, stdout);
			printf("\n");
		}  
     
	}
 
	return 0;    
}  
