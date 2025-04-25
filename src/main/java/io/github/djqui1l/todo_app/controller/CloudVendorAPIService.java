package io.github.djqui1l.todo_app.controller;

import io.github.djqui1l.todo_app.model.CloudVendor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorAPIService {

    CloudVendor cloudVendor;

    private Map<String, CloudVendor> vendorMap = new HashMap<>();


    @GetMapping("/{vendorId}")
    public CloudVendor getCloudVendorDetails(@PathVariable String vendorId){

        CloudVendor cloudVendor = vendorMap.get(vendorId);
        if (cloudVendor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
        }

//        return new CloudVendor("C1", "Vendor 1", "Address One", "xxxxx");
        return cloudVendor; //return the current couldVendor obj
    }

    @PostMapping
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
//        this.cloudVendor = cloudVendor;
        vendorMap.put(cloudVendor.getVendorId(), cloudVendor);
        return "Cloud Vendor Created Successfully.";
    }

    @PutMapping("/{vendorId}")
    public String updateCloudVendorDetails(@PathVariable String vendorId,
                                           @RequestBody CloudVendor updatedVendor){

        if (!vendorMap.containsKey(vendorId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
        }

        // Optional: Ensure the path ID and body ID match
        if (!vendorId.equals(updatedVendor.getVendorId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vendor ID mismatch");
        }

        vendorMap.put(vendorId, updatedVendor);
        return "Cloud vendor updated Successfully.";
    }

    @DeleteMapping("/{vendorId}")
    public String deleteCloudVendorDetails(@PathVariable String vendorId){

        if (!vendorMap.containsKey(vendorId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
        }

        vendorMap.remove(vendorId);
        return "Cloud vendor deleted Successfully.";
    }



}