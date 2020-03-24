import React, { FC, useContext } from 'react';
import { EventContext, Styled } from 'create-react-web-component';
import { IComponentProperties, IComponentAttributes } from './componentProperties';
import styles from './App.css';

interface IProps extends IComponentProperties, IComponentAttributes {}

const App: FC<IProps> = (props) => {
  const dispatch = useContext(EventContext);

  const handleClick = () => {
    const event = new Event('my-event');
    dispatch(event);
  };

  const renderPlugins = props.plugins.map((plugin: any) => (
    <li key={plugin.id} className='todo-title'>
      {plugin.name}
    </li>
  ));


  return (
    <Styled styles={styles}>
      <div className='app'>
        <div className='header-title'>Plugins from CMS</div>
        <div className='todo-list'>
          <ul>{renderPlugins}</ul>
        </div>
      </div>
    </Styled>
  );
};

export default App;
