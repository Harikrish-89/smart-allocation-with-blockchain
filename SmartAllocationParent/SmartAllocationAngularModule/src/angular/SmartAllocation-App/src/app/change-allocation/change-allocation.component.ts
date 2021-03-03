import { Component, OnInit, Input } from "@angular/core";
import { Allocation } from "../allocation";
import { ModifyAllocationServiceService } from "../modify-allocation-service.service";
import { Observable, BehaviorSubject } from "rxjs";
import { UserDataServiceService } from "../user-data-service.service";
import { AllocationChangeRequest } from "../allocationChangeRequest";

@Component({
  selector: "app-change-allocation",
  templateUrl: "./change-allocation.component.html",
  styleUrls: ["./change-allocation.component.css"]
})
export class ChangeAllocationComponent implements OnInit {
  @Input()
  allocationChangeRequest: AllocationChangeRequest;

  public txId: BehaviorSubject<String> = new BehaviorSubject<String>("");

  public isModificationSuccess: BehaviorSubject<boolean> = new BehaviorSubject(
    false
  );

  public isModificationErrored: BehaviorSubject<boolean> = new BehaviorSubject(
    false
  );

  constructor(
    private modifyAllocationService: ModifyAllocationServiceService,
    private userDataService: UserDataServiceService
  ) {}

  ngOnInit() {}

  onSubmit() {
    this.allocationChangeRequest.status = "Pending";
    let transId:String = Math.random()
    .toString(36)
    .substr(2, 9);
    this.allocationChangeRequest.requestId = transId
    this.txId.next(transId);
    this.allocationChangeRequest.clientId = this.userDataService.applicationUser.userDisplayName;
    this.modifyAllocation();
  }

  /**
   * modifyAllocation
   */
  private modifyAllocation(): void {
    this.modifyAllocationService.modifyAllocation(this.allocationChangeRequest).subscribe(
      data => {
        this.isModificationSuccess.next(true);
      },
      error => {
        this.isModificationErrored.next(true);
      }
    );
  }

  getInvstFrozenConfig(event) {
    this.allocationChangeRequest.previousAllocation.sector.funds[0].invstFrozenConfig = "" + event;
  }
}
