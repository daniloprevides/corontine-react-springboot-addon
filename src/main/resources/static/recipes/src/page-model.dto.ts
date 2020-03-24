import { PluginDto } from './plugin.dto';

export class Attribute {
  id: string | undefined;
  constructor(public name: string, public value: any, public type: string) {}
}

export class FieldItem {
  columns = "2";
  fieldName: string | undefined;
  eventName: string | undefined;
  eventId: string | undefined;
  eventPath: string | undefined;
  componentId: string | undefined;
  component: any;
  page: string = "";
  api: string = "";

  constructor(
    public id: string,
    public name: string,
    public modelComponent: any,
    public customElement: any,
    public masterElement: any,
    public attributes: Array<Attribute>
  ) {}
}

export class PageModel {
  api: string | undefined;
  apiData: PluginDto | undefined;
  type: string | undefined;
  apiType: string | undefined;
  validate: boolean = true;
  permissionView: string | undefined;
  permissionAdd: string | undefined;
  permissionDelete: string | undefined;

  constructor(
    public name: string,
    public description: string,
    public style: string,
    public items: Array<FieldItem>
  ) {}
}
