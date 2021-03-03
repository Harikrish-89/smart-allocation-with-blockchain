import { Component, OnInit, ViewChild } from "@angular/core";
import { ViewAllocationServiceService } from "../view-allocation-service.service";
import { Allocation } from "../allocation";
import { SelectionModel } from "@angular/cdk/collections";
import { ChangeAllocationComponent } from "../change-allocation/change-allocation.component";
import { ApprovedAllocationServiceService } from "../approved-allocation-service.service";
import { MatTableDataSource, MatPaginator, MatSort } from "@angular/material";
import { AllocationChangeRequest } from "../allocationChangeRequest";

@Component({
  selector: "app-modify-allocation",
  templateUrl: "./modify-allocation.component.html",
  styleUrls: ["./modify-allocation.component.css"]
})
export class ModifyAllocationComponent implements OnInit {
  @ViewChild(ChangeAllocationComponent)
  changeAllocationComponent: ChangeAllocationComponent;
  public allocationChangeRequests: AllocationChangeRequest[];
  displayedColumns: string[] = [
    "select",
    "requestId",
    "sector",
    "createdTime",
    "clientOrg",
    "mutualFund",
    "targetWeight",
    "minimumTradeSize",
    "invstFrozenConfig",
    "overDraftLimit",
    "targetCash"
  ];

  public selectedAllocationChangeRequest: SelectionModel<AllocationChangeRequest> = new SelectionModel<
  AllocationChangeRequest
  >(false, []);
  public isLoading: boolean = true;
  public isError: boolean = false;

  public isModificationError: boolean = false;

  public isModificationSuccess: boolean = false;
  
  public txId: String;

  constructor(private approvedAllocationService: ApprovedAllocationServiceService) {}

  public allocationChangeRequestsDataSource: MatTableDataSource<AllocationChangeRequest>;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  @ViewChild(MatSort) sort: MatSort;
  
  ngOnInit() {
    this.loadAllocation();
  }

  private loadAllocation() {
    this.approvedAllocationService.getApprovedAllocations().subscribe(
      data => {
        this.allocationChangeRequests = data;
        this.isLoading = false;
        this.allocationChangeRequestsDataSource = new MatTableDataSource(this.allocationChangeRequests);
        this.allocationChangeRequestsDataSource.paginator = this.paginator;
        this.allocationChangeRequestsDataSource.sort = this.sort;
      },
      error => {
        this.isLoading = false;
        console.log(error.message);
        this.isError = true;
      }
    );
  }

  ngAfterViewInit() {
    this.changeAllocationComponent.isModificationErrored.subscribe(data => {
      this.isModificationError = data;
      this.loadAllocation();
    });
    this.changeAllocationComponent.isModificationSuccess.subscribe(data => {
      this.isModificationSuccess = data;
      this.loadAllocation();
    });
    this.changeAllocationComponent.txId.subscribe(data => this.txId = data)
  }
}
