import groovy.json.JsonSlurper

import java.nio.file.Paths

JsonSlurper slurper = new JsonSlurper()
def policydetail

Paths.get('C:/mongo/EGit_Repositories/rest-customerpolicy/rest-customerpolicy/src/main/resources/policydetail.json').withReader { reader ->
    policydetail = slurper.parse(reader)
}


println policydetail

def vehicle = slurper.parseText '''{
            "id": "2343343",
            "make": "Acura",
            "model": "MDX",
            "vin": "353243aaa1964",
            "year": 2008
        }')'''
        

def vehicle2 = slurper.parseText '''{
            "id": "2343343",
            "make": "Audi",
            "model": "Q7",
            "vin": "35asdf3aaa1964",
            "year": 2015
        }')'''        

def POLICY = "AA 816410"        
policydetail.find {it.policyNumber.equals(POLICY)}.vehicles = policydetail.find {it.policyNumber.equals(POLICY)}.vehicles.plus(vehicle)      

policydetail.find {it.policyNumber.equals(POLICY)}.vehicles.removeAll {it.id.equals("63f00821-5880-4311-bbc9-2d86d5ef38fa")}

def idx = policydetail.find {it.policyNumber.equals(POLICY)}.vehicles.findIndexOf {it.id.equals("2343343")}
 policydetail.find {it.policyNumber.equals(POLICY)}.vehicles[idx] = vehicle2