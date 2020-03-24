/*!
* Use the two interfaces and two objects below to register
* the properties and attributes the Web Component is expected to receive.
* These will be passed down as props to the React App underneath.
*/

import { PluginDto } from "./plugin.dto";
import { PageModel, FieldItem } from "./page-model.dto";
import { FindParamsDto } from "./find-params.dto";

export interface PaginatedResponse<T = any>{
  items:Array<T>;
  itemCount: number;
  totalItems:number;
  pageCount:number;
  next:string;
  previous:string;
}

type getList<T = any> = (params?:FindParamsDto, url?: string) => Promise<PaginatedResponse<T>>;
type getOne<T = any> = (url?: string) => Promise<T>;
type createItem<T = any> = (item:any, url?: string) => Promise<T>;
type updateItem<T = any> = (item:any, url?: string, ) => Promise<T>;
type deleteItem = (id:string,url?: string) => Promise<void>;

/**
 * Update this interface to reflect the types of the properties
 */
export interface IComponentProperties {
  plugins: Array<PluginDto>;
  api: PluginDto;
  pageapi: PluginDto;
  scopes: Array<String>;
  page: PageModel;
  getList: getList;
  getOne: getOne;
  createItem: createItem;
  updateItem: updateItem;
  deleteItem: deleteItem;
}

/**
 * Update this interface to reflect the attributes of the Web Component
 * NB: The type of an attribute must be primitive
 */
export interface IComponentAttributes {
  componentTitle: string;
}

/**
 * Update this object with the initial values of the properties
 */
export const componentProperties: IComponentProperties = {
  plugins: new Array<PluginDto>(),
  api: new PluginDto(),
  pageapi: new PluginDto(),
  scopes: new Array<String>(),
  page: new PageModel("","","",new Array<FieldItem>()),
  getList: async (params?:FindParamsDto, url?: string) => {
    return {} as PaginatedResponse;
  },
  getOne: async (url?: string) => {
    return null;
  },
  createItem: async (item:any, url?: string) => {
    return null;
  },
  updateItem: async (tem:any, url?: string) => {
    return null;
  },
  deleteItem: async (id:string,url?: string) => {

  }
};

/**
 * Update this object with the initial values of the attributes
 */
export const componentAttributes: IComponentAttributes = {
  componentTitle: 'Recipes Component',
};
