import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Allocation } from "./allocation";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { UserDataServiceService } from "./user-data-service.service";
import { AllocationChangeRequest } from "./allocationChangeRequest";

@Injectable({
  providedIn: "root"
})
export class ReviewedAllocationServiceService {
  constructor(private httpClient: HttpClient,
    private userDataService: UserDataServiceService) {}

  static readonly reviewedAllocationPostUrl =
  environment.service_backend_url +"/smartallocation/reviewed";

 public postReviewedAllocation(allocationChangeRequest: AllocationChangeRequest) : Observable<Object> {
    return this.httpClient.post (ReviewedAllocationServiceService.reviewedAllocationPostUrl,
      {
        allocation: allocationChangeRequest,
        smartAllocationRequestEntity: {creds: this.userDataService.encodedCreds,
        role: this.userDataService.applicationUser.selectedRole}
      }
    );
  }
}
