Go to the server's home page to view API

Web Gui for docker - portainer.io 
- username: admin password: password

If you plan on editing docker stuff,
you should add yourself as a user

to do such enter:

sudo usermod -aG docker $USER

and then verify that it worked:

docker run hello-world