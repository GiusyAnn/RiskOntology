# RiskOntology

## Introduction

Risk Ontology is a university project for Intelligent Web exam. I realized an ontology to show information about the process of Risk Management used into Software Project Management.
Nowadays, Risk Management is not applied enough, especially because many Project Managers have no experience in this regard.
To compensate for these shortcomings, RiskOntology provides all the information necessary to have a global knowledge on the risks, the causes and the artifacts to be created.
In addition, there are example projects, so that based on them, the Project Managers can understand what risks can occur in analogy to the similarity between the project shown and their own.
N.B. Unfortunately there are not many examples as it is not easy to identify risk datasets according to software projects. We laughed at the expansion of such examples with a more substantial dataset for future developments.

## Installation

If you want to install the web app you have to:
1. Download Apache Jena Fuseki  and install it on your machine;
2. When the installation is finished, you have to start Jena Fuseki from cmd. Go to the directory where you installed it, open cmd, execute the command ```fuseki-server```;
3. When it is started, you can access to control panel of Jena Fuseki from http://localhost:3030/#/;
4. Next step is to create a dataset on Jena Fuseki called “Risk” and with the button "add data" you have to import the file “Risk.ttl”;
5. Now open the project on Intellij, execute the configuration to build the project and deploy it to Tomcat (you must have at least Tomcat 9 installed and running on your pc).
Now you can enjoy the project!

## Import ontology in Protégé

Download the file Risk.owl (not .ttl!) from ontology folder, open Protégé > File > Open... and import the owl file.
